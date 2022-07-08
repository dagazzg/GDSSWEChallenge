## Minimum Requirements 
OpenJDK 18 or other Version 18-based (Zulu, Amazon etc)
Maven for building and dependency management

### What kind of testing would you perform on the application in addition to unit testing?
Integration / E2E tests:
Currently the controller and service level unit tests only check that the files  with good / bad data are accepted / 
rejected respectively with the correct status codes (for controller) and the correct number of .save method calls
(for service). 

It doesn't actually verify the accuracy of the data that is inserted through the csv, neither does it
verify the JSON body of the responses from the endpoints against a schema. 

To do so we can write integration tests using *REST-assured* and *cucumber* that emulates a someone calling the
endpoints with our defined test data and asserting that the response status code is correct, and that the response
body matches the expected json schema specified in the contract.

Load testing should be done as well where multiple files and users are querying the APIs at once, scaling strategies
to be covered below.

### How would you scale up the system to handle potentially millions of files?
The easiest way would be to spin up multiple instances of the API service, and have a load-balancer split request traffic
between the instances, autoscaling up when load is high and down when demand is low to keep costs down. However this is
a 'brute force' approach that throws additional resources at the problem. Even if we keep scaling up the API, the database
will eventually become the bottleneck unless we do DB optimisation strategies like sharding and other costly measures as well.

As the system grows, the user data querying and data uploading services may need to be separated into their own microservices
in order to scale more efficiently through horizontal partitioning

### CSV files can be very large and take a long time to process. This can be a problem using HTTP POST calls. How would you update the design to handle HTTP POST requests that can potentially take a long time to execute?
Decouple the file upload from the actual processing by turning the request into an asynchronous one. 
Instead of directly processing the csv file, it creates an upload request with a unique transaction number that is 
returned to the user (HTTP 202) and the actual upload payload is sent to a
message queue that can hold all the incomplete upload requests.

Using this unique transaction number as an identifier, create a new endpoint to query the status of the upload request
using the transaction number, while the actual file processing logic in UploadService should be offloaded to another worker
that reads requests from the message queue, and runs independently against the DB. When the upload is completed or failed,
the status is updated in a separate table dedicated to only storing the status of transactions (The status endpoint should
also be referring to this table for data). Bonus: Create a notification system that informs the user when the status
has changed from 'processing' to 'completed' / 'failed'
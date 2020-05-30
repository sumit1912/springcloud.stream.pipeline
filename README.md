# springcloud.stream.pipeline
This is a pipeline project developed as Spring Cloud Data Flow applications(source-app, processor-app and sink-app) to take in document in the form of JSON.
The source-app takes-in JSON data via REST and puts it on Message channel to be consumed by processor-app.
processor-app does nothing but accepts the data in reactive format and just passes it.
sink-app accepts the data in reactive format and logs the data.

These applications are designed to run on SCDF with RabbitMQ message broker.

Data can be sent via REST client to source-app in below format:
 
```json
        {
            "content": "This is the sample input data.",
            "id": "DocumentId123"
        }
```

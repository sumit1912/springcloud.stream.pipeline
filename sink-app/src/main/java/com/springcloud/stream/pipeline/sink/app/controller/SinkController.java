package com.springcloud.stream.pipeline.sink.app.controller;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.stream.pipeline.core.PipelineDocument;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class SinkController {

    /**
     * Extracts the StorageDocuments from PipelineDocuments, adds NER tags and then
     * wrap it in RestUploadDocument and send the document to be stored in DB
     *
     * @param pipelineDocument The reactive data type Flux&lt;PipelineDocument&gt;
     *                         received from the source or processor from the Spring
     *                         cloud data pipeline
     */
    @StreamListener(Sink.INPUT)
    public void saveData(Flux<PipelineDocument> pipelineDocument) {

        pipelineDocument
                .subscribe(p -> LOGGER.info("Recieved Doc with Id: {} and content: {}", p.getId(), p.getContent()));

    }

}

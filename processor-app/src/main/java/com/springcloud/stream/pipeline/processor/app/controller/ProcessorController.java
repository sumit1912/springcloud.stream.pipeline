package com.springcloud.stream.pipeline.processor.app.controller;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;
import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.stream.pipeline.core.PipelineDocument;

import reactor.core.publisher.Flux;

@RestController
public class ProcessorController {


    /**
     * Spring cloud data flow Processor to convert pipelineDocument content to upper case
     *
     * @param pipelineDocument The reactive data type Flux&lt;PipelineDocument&gt;
     *     received from the source or processor from the Spring
     *     cloud data pipeline
     * @return A reactive data type Flux&lt;PipelineDocument&gt;
     */
    @StreamListener(INPUT)
    @SendTo(OUTPUT)
    public Flux<PipelineDocument> receive(Flux<PipelineDocument> pipelineDocument) {
        // Just converts pipelineDocument content to upper case
        return pipelineDocument.map(p -> p.setContent(p.getContent().toUpperCase())).log();
    }
}

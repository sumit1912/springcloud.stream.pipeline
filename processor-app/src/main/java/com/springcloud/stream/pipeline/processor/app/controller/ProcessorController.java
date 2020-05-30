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
     * Spring cloud data flow Processor to split sentences present in the passed
     * document
     *
     * @param pipelineDocument The reactive data type Flux&lt;PipelineDocument&gt;
     *     received from the source or processor from the Spring
     *     cloud data pipeline
     * @return A reactive data type Flux&lt;PipelineDocument&gt; containing multiple
     *     instances of PipelineDocument obtained after splitting single
     *     PipelineDocument
     */
    @StreamListener(INPUT)
    @SendTo(OUTPUT)
    public Flux<PipelineDocument> receive(Flux<PipelineDocument> pipelineDocument) {
        // Purposefully removed implementation just to keep it simple
        return pipelineDocument.log();
    }
}

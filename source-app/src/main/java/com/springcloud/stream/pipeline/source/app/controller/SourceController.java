package com.springcloud.stream.pipeline.source.app.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.stream.pipeline.core.PipelineDocument;
import com.springcloud.stream.pipeline.core.UploadResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SourceController {
    private Source source;

    @Autowired
    public SourceController(Source source) {
        this.source = source;
    }

    /**
     * A source module that listens for HTTP requests and emits the body as a
     * message payload.
     *
     * @param pipelineDocument The data uploaded by HTTP POST method
     * @param contentType The HTTP Content-Type
     * @return returns UploadResponse containing HTTP POST status and number of
     *     received document message.
     */
    @PostMapping(
        path = {"${upload.path}"},
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public UploadResponse uploadData(@RequestBody PipelineDocument pipelineDocument,
            @RequestHeader(HttpHeaders.CONTENT_TYPE) MediaType contentType) {
        
        boolean sendMsgStatus = sendMessage(pipelineDocument, contentType);
        if (!sendMsgStatus) {
            return new UploadResponse(HttpStatus.OK.toString(), "Failed to upload document.");
        }
        return new UploadResponse(HttpStatus.OK.toString(), "Successfully uploaded document.");
    }

    private boolean sendMessage(PipelineDocument pipelineDocument, MediaType contentType) {
        boolean sentStatus = false;
        try {
            sentStatus = source.output().send(MessageBuilder.createMessage(pipelineDocument,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
        } catch (MessageHandlingException e) {
            LOGGER.error("Failed to upload document with id:" + pipelineDocument.getId(), e.getCause());
        }
        return sentStatus;
    }
}

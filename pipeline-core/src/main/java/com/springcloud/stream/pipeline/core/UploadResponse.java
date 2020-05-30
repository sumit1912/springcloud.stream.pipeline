package com.springcloud.stream.pipeline.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors
public class UploadResponse {
    @NonNull
    private String status;
    private String message;
}

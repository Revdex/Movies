package com.digitu.movies.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonProperty("status_message")
    private String statusMessage;
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("status_code")
    private long statusCode;

    /**
     * @return The statusMessage
     */
    @JsonProperty("status_message")
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage The status_message
     */
    @JsonProperty("status_message")
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public ErrorResponse withStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    /**
     * @return The success
     */
    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    @JsonProperty("success")
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * @return The statusCode
     */
    @JsonProperty("status_code")
    public long getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode The status_code
     */
    @JsonProperty("status_code")
    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorResponse withStatusCode(long statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public String toString() {
        String data = "";
        data += "Code" + "[" + statusCode + "]";
        data += "Success" + "[" + success + "]";
        data += "Message" + "[" + statusMessage + "]";
        data += "\n";
        return data;
    }
}
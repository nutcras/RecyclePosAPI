package com.example.sellWebApp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Arpit Khandelwal
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class ResponseApi<T> {
//    @Schema(description = "สถานะการตอบกลับ โดยมีดังนี้ OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY")
    private Status status;
//    @Schema(description = "ก้อนข้อมูลที่จะส่งไป")
    private T payload;
//    @Schema(description = "code ของ error เช่น 200 404 403")
    private Object errors;
//    @Schema(description = "รายละเอียดของหน้า error")
    private PageInfo pageInfo;

    public ResponseApi() {
        this.status = Status.OK;
    }

    public ResponseApi(Status status) {
        this.status = status;
    }

    /**
     * //    public ResponseApi(HttpStatus status) {
     * //        this.status = switch (status) {
     * //            case OK -> Status.OK;
     * //            case BAD_REQUEST -> Status.BAD_REQUEST;
     * //            case UNAUTHORIZED -> Status.UNAUTHORIZED;
     * //            case NOT_ACCEPTABLE -> VALIDATION_EXCEPTION;
     * //            case FORBIDDEN -> Status.WRONG_CREDENTIALS;
     * //            case METHOD_NOT_ALLOWED -> Status.ACCESS_DENIED;
     * //            case NOT_FOUND -> Status.NOT_FOUND;
     * //            default -> Status.EXCEPTION;
     * //        };
     * //    }
     */

    public enum Status {
        OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageInfo {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int page;

        public PageInfo(int size, long totalElements, int totalPages, int page) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.page = page + 1;
        }



    }

}


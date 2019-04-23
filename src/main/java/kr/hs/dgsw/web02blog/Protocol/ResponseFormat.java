package kr.hs.dgsw.web02blog.Protocol;

import lombok.Data;

@Data
public class ResponseFormat {
    private int code;
    private String desc;
    private Object data;

    public ResponseFormat(ResponseType responseType, Object data,Object option){
        this.code = responseType.code();
        this.desc = option instanceof Long || option instanceof String ?
                    String.format(responseType.desc(), option) : responseType.desc();
        this.data = data;
    }

    public ResponseFormat(ResponseType responseType, Object data){
        this(responseType, data, null);
    }
}

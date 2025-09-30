package com.frknozbek.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;


    String prepareErrorMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append(messageType.getMessage());

        if (this.ofStatic != null) {
            builder.append(" : " + ofStatic);
        }
        return builder.toString();
    }

}

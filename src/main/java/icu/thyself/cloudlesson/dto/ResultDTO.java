package icu.thyself.cloudlesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoyelun
 * @date 2020/5/2 21:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Integer code;
    private String message;
}

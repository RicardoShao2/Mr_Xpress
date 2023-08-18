package capstone.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;

    private String mobileNumber;

    private String email;

    private String address;

    private  int status;

    private  String password;

    private int type;

    private LocalDateTime createTime;

}

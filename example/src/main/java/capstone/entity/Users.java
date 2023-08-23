package capstone.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {
    @TableId(type = IdType.AUTO)
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

package capstone.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class GenerateTool {
    public String generateVerificationCode() {
        // 这里是一个简单的示例，你可以使用更复杂的方法生成验证码。
        return UUID.randomUUID().toString().substring(0, 6);
    }
}

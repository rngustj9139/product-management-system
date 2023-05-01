package koo.product.management.system.springTypeConverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // equals(두 객체의 내용이 같은지 동등성을 비교하는 연산자), hashCode(두 객체가 같은 객체인지 동일성을 비교하는 연산자) 자동 생성
public class IpPort { // "127.0.0.1:8080" 이라는 문자를 IpPort라는 객체로 변환

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

}

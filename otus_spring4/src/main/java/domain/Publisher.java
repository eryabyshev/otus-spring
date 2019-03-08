package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Publisher {
    private long id;
    private String name;
    private String address;
    private String phone;
}

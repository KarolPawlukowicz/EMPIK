package com.pawlukowicz.empik.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NonNull String login;
    private int requestCount = 1;

    public void incrementRequestCount() {
        requestCount++;
    }

}

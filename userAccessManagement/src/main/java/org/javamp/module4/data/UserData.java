package org.javamp.module4.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserData {
    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "failedloginattempts")
    private int failedLoginAttempts;

    @Column(name = "blockingtimestamp")
    private Long blockingTimestamp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AuthoritiesData> authorities;

    public boolean isDisabled(){
        return !enabled;
    }
}

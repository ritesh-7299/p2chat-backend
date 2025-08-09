package com.ritesh.p2chat_backend.connection;

import com.ritesh.p2chat_backend.system.BaseEntity;
import com.ritesh.p2chat_backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "connections")
public class Connection extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user", foreignKey = @ForeignKey(name = "fk_from_user_id"))
    private User fromUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user", foreignKey = @ForeignKey(name = "fk_to_user_id"))
    private User toUser;
}

package com.example.CMS_01.POJO;

import com.example.CMS_01.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapper {
    private User user;
    private User NewCred;
}

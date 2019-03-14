package com.study.selfs.gupao.springboot.jdbc.domain;

import com.study.selfs.gupao.springboot.validation.annotation.NameValidation;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;
import java.util.Date;

public class NormalCustom {


    @NotBlank(message = "姓名不能为空格")
    @NotNull(message = "姓名不能为空")
//    @Pattern(regexp = "^gupao_\\W{1,}$" ,message="姓名必须以gupao_为前缀")
    @NameValidation
    private String name;

    @Positive
    private Integer location;

    @Max(value = 120,message = "年龄不能超过120岁")
    private Integer age;

    @Range(min = 0,max = 1,message = "性别必须是（男：1）或（女：0）")
    private Integer sex;

    private Integer isVip;

    private Date createDate;

    private String job;

    private Integer number;

    private String  addr;

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    @Override
    public String toString() {
        return "NormalCustom{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", isVip=" + isVip +
                ", createDate=" + createDate +
                ", job='" + job + '\'' +
                ", number=" + number +
                ", addr='" + addr + '\'' +
                '}';
    }


}

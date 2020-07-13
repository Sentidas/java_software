package ru.sentidas.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String mobile;
  private  int id;
  private String group;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  public ContactData(String firstname, String lastname, String address, String email, String mobile, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
    this.id = Integer.MAX_VALUE;
  }


  public ContactData(int id, String firstname, String lastname, String address, String email, String mobile,  String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", id=" + id +
            ", group='" + group + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }
}

package my.app.momschoice.chefFoodPanel;

public class Chef {
    private String Area, ConfirmPassword, Emailid, Fname, House, Lname, Mobile, Password, Postcode, State,Pincode;

    // Press Alt+Insert


    public Chef(String area, String confirmPassword, String emailid, String fname, String house, String lname, String mobile, String password, String postcode, String state) {
        this.Area = area;

        ConfirmPassword = confirmPassword;
        Emailid = emailid;
        Fname = fname;
        House = house;
        Lname = lname;
        Mobile = mobile;
        Password = password;
        Postcode = postcode;
        State = state;
    }
    public Chef() {
    }

    public String getArea() {
        return Area;
    }



    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public String getEmailid() {
        return Emailid;
    }

    public String getFname() {
        return Fname;
    }

    public String getHouse() {
        return House;
    }

    public String getLname() {
        return Lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getState() {
        return State;
    }

    public void setArea(String area) {
        Area = area;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setHouse(String house) {
        House = house;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public void setState(String state) {
        State = state;
    }
}

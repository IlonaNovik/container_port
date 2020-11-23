package GUI;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sender {
    public String firstName;
    public String lastName;
    public long pesel;
    public String address;
    public LocalDate birthDate;
    public ArrayList<IrresponsibleSenderWithDangerousGoods> warnings = new ArrayList<IrresponsibleSenderWithDangerousGoods>();

    /** Sender constructor */
    public Sender(String firstName, String lastName, String pesel, String address, LocalDate birthDate) throws Exception {
        if(pesel.length() == 11){
            this.firstName = firstName;
            this.lastName = lastName;
            this.pesel = Long.parseLong(pesel);
            this.address = address;
            this.birthDate = birthDate;
        }else{
            throw new Exception("Pesel number must contain 11 integers");
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

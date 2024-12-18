package DB_TestSelf.VO;

public class FamilyVO {
    private String familyPos;
    private String fName;
    private int fAge;
    private int fHeight;

    public FamilyVO(String familyPos, String fName, int fAge, int fHeight) {
        this.familyPos = familyPos;
        this.fName = fName;
        this.fAge = fAge;
        this.fHeight = fHeight;
    }

    public FamilyVO() {
    }

    public String getfamilyPos() {
        return familyPos;
    }

    public String getfName() {
        return fName;
    }

    public int getfAge() {
        return fAge;
    }

    public int getfHeight() {
        return fHeight;
    }
}

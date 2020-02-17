package sabouni.margaux.app;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.HashMap;
        import java.util.Map;

public class Name {

    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("first")
    @Expose
    private String first;

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }


}

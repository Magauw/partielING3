package sabouni.margaux.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Nationality {

    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("nationnality")
    @Expose
    private String nationnality;

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getNationnality() {
            return nationnality;
        }

        public void setNationnality(String nationnality) {
            this.nationnality = nationnality;
        }

    }


package comhala.halawyat.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SettingModel {


    public class Data {

        @SerializedName("about_us")
        @Expose
        private String aboutUs;
        @SerializedName("terms")
        @Expose
        private String terms;
        @SerializedName("common_questions")
        @Expose
        private String commonQuestions;

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public String getTerms() {
            return terms;
        }

        public void setTerms(String terms) {
            this.terms = terms;
        }

        public String getCommonQuestions() {
            return commonQuestions;
        }

        public void setCommonQuestions(String commonQuestions) {
            this.commonQuestions = commonQuestions;
        }

    }


    public class GetSetting {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("data")
        @Expose
        private Data data;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }
}

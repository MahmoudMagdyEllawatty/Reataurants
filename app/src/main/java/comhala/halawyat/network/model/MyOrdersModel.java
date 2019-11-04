package comhala.halawyat.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOrdersModel {
  

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("order_number")
        @Expose
        private String orderNumber;
        @SerializedName("order_date")
        @Expose
        private String orderDate;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("total_cost")
        @Expose
        private String totalCost;
        @SerializedName("shipping_fees")
        @Expose
        private String shippingFees;
        @SerializedName("shop_name")
        @Expose
        private String shopName;
        @SerializedName("delegate_name")
        @Expose
        private String delegateName;
        @SerializedName("product_array")
        @Expose
        private List<ProductArray> productArray = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(String totalCost) {
            this.totalCost = totalCost;
        }

        public String getShippingFees() {
            return shippingFees;
        }

        public void setShippingFees(String shippingFees) {
            this.shippingFees = shippingFees;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getDelegateName() {
            return delegateName;
        }

        public void setDelegateName(String delegateName) {
            this.delegateName = delegateName;
        }

        public List<ProductArray> getProductArray() {
            return productArray;
        }

        public void setProductArray(List<ProductArray> productArray) {
            this.productArray = productArray;
        }

    }


    public class Get_MyOrder {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("msg")
        @Expose
        private Object msg;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

    }



        public class ProductArray {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("quantity")
            @Expose
            private String quantity;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }
        }

}

package com.kekemei.kekemei.bean;

import java.io.Serializable;
import java.util.List;

public class MeiRongShiListBean {
    private List<BannerBean> banner;
    private List<BeauticianBean> data;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<BeauticianBean> getData() {
        return data;
    }

    public void setData(List<BeauticianBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 美容师
         * user_id : 4
         * redenvelopes_ids : 0
         * coupon_ids : 0
         * image : /uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg
         * images : /uploads/20181022/2c5d84349965e653baa9870a5311d15d.jpg,/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg,/uploads/20181022/d92905316745b7ce3c0e3d6ddc382c98.jpg
         * shop_shop_ids : 1,2
         * content : 3333333
         * place :
         * speciality :
         * identity :
         * state : 1
         * shops : [{"id":1,"name":"西直门店","image":"/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg","images":"/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg,/uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg,/uploads/20181022/223862141ebbdec2dbcbbd0d976383e0.jpeg","city":"北京/北京市/西城区","redenvelopes_ids":1,"coupon_ids":1,"address":"2222","tel":"2222","longitude":"2222","latitude":"2222","content":"2222","state":"1","createtime":1537862188,"shop_service_ids":"3,1,2","user_id":1,"distance":4343.33,"state_text":"正常"},{"id":2,"name":"德胜门店","image":"/uploads/20181022/223862141ebbdec2dbcbbd0d976383e0.jpeg","images":"/uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg,/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg","city":"北京/北京市/西城区","redenvelopes_ids":1,"coupon_ids":1,"address":"德胜门店","tel":"111111","longitude":"116.3975157338","latitude":"39.9088596409","content":"11111","state":"1","createtime":1538567762,"shop_service_ids":"3,1,2","user_id":1,"distance":10.18,"state_text":"正常"}]
         * user_nickname : 13269501766
         * order_count : 2
         * comment_count : 0
         * isfriend : 0
         * satisfaction : 0
         * tag : [""]
         * tag_text : [null]
         * distance : 10.18
         * state_text : 正常
         * start : 3
         */

        private int id;
        private String name;
        private int user_id;
        private int redenvelopes_ids;
        private int coupon_ids;
        private String image;
        private String images;
        private String shop_shop_ids;
        private String content;
        private String place;
        private String speciality;
        private String identity;
        private String state;
        private String user_nickname;
        private int order_count;
        private int comment_count;
        private int isfriend;
        private int satisfaction;
        private double distance;
        private String state_text;
        private int start;
        private List<ShopsBean> shops;
        private List<String> tag;
        private List<String> tag_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getRedenvelopes_ids() {
            return redenvelopes_ids;
        }

        public void setRedenvelopes_ids(int redenvelopes_ids) {
            this.redenvelopes_ids = redenvelopes_ids;
        }

        public int getCoupon_ids() {
            return coupon_ids;
        }

        public void setCoupon_ids(int coupon_ids) {
            this.coupon_ids = coupon_ids;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getShop_shop_ids() {
            return shop_shop_ids;
        }

        public void setShop_shop_ids(String shop_shop_ids) {
            this.shop_shop_ids = shop_shop_ids;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getIsfriend() {
            return isfriend;
        }

        public void setIsfriend(int isfriend) {
            this.isfriend = isfriend;
        }

        public int getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(int satisfaction) {
            this.satisfaction = satisfaction;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public List<String> getTag_text() {
            return tag_text;
        }

        public void setTag_text(List<String> tag_text) {
            this.tag_text = tag_text;
        }

        public static class ShopsBean implements Serializable {
            /**
             * id : 1
             * name : 西直门店
             * image : /uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg
             * images : /uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg,/uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg,/uploads/20181022/223862141ebbdec2dbcbbd0d976383e0.jpeg
             * city : 北京/北京市/西城区
             * redenvelopes_ids : 1
             * coupon_ids : 1
             * address : 2222
             * tel : 2222
             * longitude : 2222
             * latitude : 2222
             * content : 2222
             * state : 1
             * createtime : 1537862188
             * shop_service_ids : 3,1,2
             * user_id : 1
             * distance : 4343.33
             * state_text : 正常
             */

            private int id;
            private String name;
            private String image;
            private String images;
            private String city;
            private int redenvelopes_ids;
            private int coupon_ids;
            private String address;
            private String tel;
            private String longitude;
            private String latitude;
            private String content;
            private String state;
            private int createtime;
            private String shop_service_ids;
            private int user_id;
            private double distance;
            private String state_text;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getRedenvelopes_ids() {
                return redenvelopes_ids;
            }

            public void setRedenvelopes_ids(int redenvelopes_ids) {
                this.redenvelopes_ids = redenvelopes_ids;
            }

            public int getCoupon_ids() {
                return coupon_ids;
            }

            public void setCoupon_ids(int coupon_ids) {
                this.coupon_ids = coupon_ids;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getShop_service_ids() {
                return shop_service_ids;
            }

            public void setShop_service_ids(String shop_service_ids) {
                this.shop_service_ids = shop_service_ids;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public String getState_text() {
                return state_text;
            }

            public void setState_text(String state_text) {
                this.state_text = state_text;
            }
        }
    }
}

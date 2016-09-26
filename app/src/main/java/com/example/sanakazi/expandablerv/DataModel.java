package com.example.sanakazi.expandablerv;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SanaKazi on 9/21/2016.
 */
public class DataModel {
    int status;
    ArrayList<ParentModel> Message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<ParentModel> getMessage() {
        return Message;
    }

    public void setMessage(ArrayList<ParentModel> message) {
        Message = message;
    }


    public class ParentModel implements ParentListItem {
        int categoryID;
        String fullName;
        ArrayList<ChildModel> vendorList;

        public int getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(int categoryID) {
            this.categoryID = categoryID;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public ArrayList<ChildModel> getVendorList() {
            return vendorList;
        }


        public void setVendorList(ArrayList<ChildModel> vendorList) {
            this.vendorList = vendorList;
        }

        @Override
        public List<?> getChildItemList() {
            return vendorList;
        }

        @Override
        public boolean isInitiallyExpanded() {
            return false;
        }


        public class ChildModel {
            int num;
            String fullName;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
        }
    }


}








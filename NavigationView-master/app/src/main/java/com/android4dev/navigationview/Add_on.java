package com.android4dev.navigationview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by S. Harsono on 2/3/2016.
 */
public class Add_on extends Fragment {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems;
    private ExpandableListView ExpandList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_on, container, false);

        ExpandList = (ExpandableListView)v.findViewById(R.id.explist);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(getActivity(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String group_name = ExpListItems.get(groupPosition).getName();

                ArrayList<Child> ch_list = ExpListItems.get(groupPosition).getItems();

                String child_issue = ch_list.get(childPosition).getIssue_date();
                String child_editFrom = ch_list.get(childPosition).getEditFrom();
                String child_editTo = ch_list.get(childPosition).getEditFrom();

                String child_from = ch_list.get(childPosition).getFrom();
                String child_to = ch_list.get(childPosition).getTo();

                String child_keyword = ch_list.get(childPosition).getKeyword();
                String child_formKeyword = ch_list.get(childPosition).getForm_keyword();
                String child_editKeyword = ch_list.get(childPosition).getEditKeyword();



                showToastMsg(group_name + "\n" + child_issue + "\n" + child_from + "\n" + child_editFrom + "\n" + child_to + "\n" + child_editTo
                        + "\n" + child_keyword + "\n" + child_formKeyword + "\n" + child_editKeyword);

                return false;
            }
        });

        ExpandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                String group_name = ExpListItems.get(groupPosition).getName();
                showToastMsg(group_name + "\n Expanded");

            }
        });

        ExpandList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                String group_name = ExpListItems.get(groupPosition).getName();
                showToastMsg(group_name + "\n Expanded");

            }
        });

        return v;

    }

    private ArrayList<Group> SetStandardGroups() {
        ArrayList<Group> group_list = new ArrayList<Group>();
        ArrayList<Child> child_list;

        // Setting Group 1
        child_list = new ArrayList<Child>();
        Group gru1 = new Group();
        gru1.setName("Search");

        Child ch1_1 = new Child();
        ch1_1.setIssue_date("Issue Date");
        ch1_1.setFrom("From");
        ch1_1.setEditFrom("");
        ch1_1.setTo("To");
        ch1_1.setEditTo("");

        ch1_1.setKeyword("Keyword");
        ch1_1.setForm_keyword("Keyword");
        ch1_1.setEditKeyword("");

       // ch1_1.setLayout(RelativeLayout);
        child_list.add(ch1_1);


        gru1.setItems(child_list);

        //listing all groups
        group_list.add(gru1);

        return group_list;
    }
    public void showToastMsg(String Msg) {
        Toast.makeText(getActivity(), Msg, Toast.LENGTH_SHORT).show();
    }

}


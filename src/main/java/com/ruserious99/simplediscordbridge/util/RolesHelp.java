package com.ruserious99.simplediscordbridge.util;

public class RolesHelp {

    public static String getrole(String[] args) {
        if (args[1].equalsIgnoreCase(Const.ADMIN)) {
            return Const.ADMIN_ID;
        }else if(args[1].equalsIgnoreCase(Const.MEMBER)){
            return Const.MEMBER_ID;
        }else if(args[1].equalsIgnoreCase(Const.HELPER)){
            return Const.HELPER_ID;
        }
        return null;
    }
}


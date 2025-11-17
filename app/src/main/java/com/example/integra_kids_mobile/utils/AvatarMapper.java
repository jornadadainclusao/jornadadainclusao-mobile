package com.example.integra_kids_mobile.utils;

import com.example.integra_kids_mobile.R;

public class AvatarMapper {

    public static int getAvatarResource(String url) {

        if (url == null) return R.drawable.player_icon_null;

        switch (url) {
            case "https://www.svgrepo.com/show/420338/avatar-person-pilot.svg":
                return R.drawable.player_icon11;

            case "https://www.svgrepo.com/show/420345/fighter-luchador-man.svg":
                return R.drawable.player_icon12;

            case "https://www.svgrepo.com/show/420315/avatar-cloud-crying.svg":
                return R.drawable.player_icon7;

            case "https://www.svgrepo.com/show/420322/avatar-female-portrait-2.svg":
                return R.drawable.player_icon9;

            case "https://www.svgrepo.com/show/420327/avatar-child-girl.svg":
                return R.drawable.player_icon6;

            case "https://www.svgrepo.com/show/420329/anime-away-face.svg":
                return R.drawable.player_icon2;

            case "https://www.svgrepo.com/show/420323/avatar-avocado-food.svg":
                return R.drawable.player_icon3;

            case "https://www.svgrepo.com/show/420333/afro-avatar-male.svg":
                return R.drawable.player_icon1;

            case "https://www.svgrepo.com/show/420360/avatar-batman-comics.svg":
                return R.drawable.player_icon4;

            case "https://www.svgrepo.com/show/420362/avatar-cacti-cactus.svg":
                return R.drawable.player_icon5;

            case "https://www.svgrepo.com/show/420343/avatar-joker-squad.svg":
                return R.drawable.player_icon10;

            case "https://www.svgrepo.com/show/420347/avatar-einstein-professor.svg":
                return R.drawable.player_icon8;

            default:
                return R.drawable.player_icon_null;
        }
    }

    public static String getAvatarUrlFromResource(int drawableId) {

        if (drawableId == R.drawable.player_icon11)
            return "https://www.svgrepo.com/show/420338/avatar-person-pilot.svg";

        if (drawableId == R.drawable.player_icon12)
            return "https://www.svgrepo.com/show/420345/fighter-luchador-man.svg";

        if (drawableId == R.drawable.player_icon7)
            return "https://www.svgrepo.com/show/420315/avatar-cloud-crying.svg";

        if (drawableId == R.drawable.player_icon9)
            return "https://www.svgrepo.com/show/420322/avatar-female-portrait-2.svg";

        if (drawableId == R.drawable.player_icon6)
            return "https://www.svgrepo.com/show/420327/avatar-child-girl.svg";

        if (drawableId == R.drawable.player_icon2)
            return "https://www.svgrepo.com/show/420329/anime-away-face.svg";

        if (drawableId == R.drawable.player_icon3)
            return "https://www.svgrepo.com/show/420323/avatar-avocado-food.svg";

        if (drawableId == R.drawable.player_icon1)
            return "https://www.svgrepo.com/show/420333/afro-avatar-male.svg";

        if (drawableId == R.drawable.player_icon4)
            return "https://www.svgrepo.com/show/420360/avatar-batman-comics.svg";

        if (drawableId == R.drawable.player_icon5)
            return "https://www.svgrepo.com/show/420362/avatar-cacti-cactus.svg";

        if (drawableId == R.drawable.player_icon10)
            return "https://www.svgrepo.com/show/420343/avatar-joker-squad.svg";

        if (drawableId == R.drawable.player_icon8)
            return "https://www.svgrepo.com/show/420347/avatar-einstein-professor.svg";

        return null;
    }


}

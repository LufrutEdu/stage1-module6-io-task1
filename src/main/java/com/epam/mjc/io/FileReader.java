package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            StringBuilder input = new StringBuilder();
            int c;
            while ((c = fileInputStream.read()) != -1) {
                input.append((char) c);
            }
            String[] profile = input.toString().split(": |\r\n");
            String name = null;
            int age = 0;
            String email = null;
            Long phone = 0L;
            for (int i = 0; i < profile.length - 1; i++) {
                switch (profile[i]) {
                    case "Name":
                        name = profile[i + 1];
                        break;
                    case "Age":
                        try {
                            age = Integer.parseInt(profile[i + 1]);
                        } catch (NumberFormatException ignored){}
                        break;
                    case "Email":
                        email = profile[i + 1];
                        break;
                    case  "Phone":
                        try {
                        phone = Long.parseLong(profile[i + 1]);
                        } catch (NumberFormatException ignored){}
                        break;
                    default:
                        break;
                }
            }
            return new Profile(name, age, email, phone);
        } catch (IOException ex) {
            System.out.println("Read file error:" + ex);
            return null;
        }

    }
}

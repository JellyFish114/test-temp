//package utils;
//
//import com.opencsv.bean.CsvToBeanBuilder;
//import com.opencsv.bean.StatefulBeanToCsv;
//import com.opencsv.bean.StatefulBeanToCsvBuilder;
//import com.opencsv.exceptions.CsvDataTypeMismatchException;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
//import netscape.javascript.JSObject;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.nio.file.Path;
//import java.util.Collection;
//import java.util.List;
//
//public class CsvUtils {
//    public static List<Object> readCsv(Path filePath, Class type) throws FileNotFoundException {
//        List<Object> beans = new CsvToBeanBuilder(new FileReader(filePath.toString())).withType(type).withSeparator('|').build().parse();
//        return beans;
//    }
//
//    public static void createCsv(String filename, Collection lines) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
//        File newCsv = new File(filename);
//        if(newCsv.createNewFile()) {
//            Writer writer = new FileWriter(filename.toString());
//            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).withEscapechar('|').build();
//            beanToCsv.write(lines);
//            writer.close();
//
//        }
//    }
//
//    private static Field getField(Object o, String fieldName) {
//        Field field = null;
//        try {
//            field = o.getClass().getDeclaredField(fieldName);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return field;
//    }
//
//    private static boolean areFieldsEqual(Object o1, String fieldName1, Object o2, String fieldName2) {
//        try {
//            Object val1 = getField(o1, fieldName1).get(o1);
//            Object val2 = getField(o2, fieldName2).get(o2);
//            return val1.equals(val2);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static void updateFieldValue(Object o1, String fieldName1, Object o2, String fieldName2) {
//        Field oldField = getField(o1, fieldName1);
//
//        try {
//            Object newValue = getField(o2, fieldName2).get(o2);
//            oldField.set(o1, newValue);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void replaceField(String oldFieldname, String newFieldname, Collection mappingList, Collection toReplaceList) {
//        for (Object oldElement : mappingList) {
//
//            Object newElement = toReplaceList.stream().filter(newELement -> areFieldsEqual(oldElement, oldFieldname, newELement, newFieldname)).findAny().orElse(null);
//
//            if (newElement != null) {
//                updateFieldValue(oldElement, oldFieldname, newElement, newFieldname);
//                System.out.println("FOUND !" + newElement);
//                break;
//            } else {
//                System.out.println("NOT FOUND !");
//            }
//            break;
//        }
//    }
//
//    public static void replaceFieldOrDeleteLine(String oldFieldname, String newFieldname, Collection mappingList, Collection toReplaceList) {
//
//        for (Object oldElement : mappingList) {
//
//            Object newElement = toReplaceList.stream().filter(newELement -> areFieldsEqual(oldElement, oldFieldname, newELement, newFieldname)).findAny().orElse(null);
//
//            if (newElement != null) {
//                updateFieldValue(oldElement, oldFieldname, newElement, newFieldname);
//                System.out.println("FOUND !" + newElement);
//                break;
//            } else {
//                mappingList.remove(oldElement);
//                System.out.println("NOT FOUND !");
//            }
//            break;
//        }
//    }
//
//    public static JSObject convertToJson() {
//        return null;
//    }
//}

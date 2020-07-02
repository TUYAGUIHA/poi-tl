package com.deepoove.poi.xwpf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

public final class XWPFChartFactory extends POIXMLFactory {
    private PackagePart part;

    public XWPFChartFactory(PackagePart xwpfChart) {
        this.part = xwpfChart;
    }

    @Override
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XWPFRelation.getInstance(relationshipType);
    }

    @Override
    protected POIXMLDocumentPart createDocumentPart(Class<? extends POIXMLDocumentPart> cls, Class<?>[] classes,
            Object[] values) throws SecurityException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        Constructor<? extends POIXMLDocumentPart> constructor = XWPFChart.class
                .getDeclaredConstructor(PackagePart.class);
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[] { part });
    }
}
package com.arpangroup.inventory.util;

import com.arpangroup.inventory.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class LogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    private LogUtils() {
    }
    static String errorCode = "";
    static JAXBContext jaxbContext;
    static {
        try {

            //jaxbContext = JAXBContext.newInstance(Ack.class, ReqDiagnostic.class, ResDiagnostic.class, PaymentRequest.class, PaymentReceipt.class);

            jaxbContext = JAXBContext.newInstance(ErrorMessage.class);

        } catch (JAXBException e) {
            LOGGER.error("Error initializing jaxb in LogUtils", e);
            jaxbContext = null;
        }
    }

    public static void logReqRespMessage(Object request, String refId, Object action) {
        if (null != action){
            //System.out.println(action.alias());
        }
        LOGGER.info("Logging thruogh logUtils object is"+request);
        LOGGER.info("Logging thruogh logUtils refid is"+refId);
        LOGGER.info("Logging thruogh logUtils action is"+action);

        try {
            if (refId == null)
                refId = "";

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter actionRequest = new StringWriter();
            jaxbMarshaller.marshal(request, actionRequest);
            String payXml = actionRequest.toString();
            // ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // jaxbMarshaller.marshal(request, baos);
            LOGGER.info(
                    "\n------------------------------------------------------------------------------------------------------------------------------------\n"
                            + " \n" + payXml
                            + "\n------------------------------------------------------------------------------------------------------------------------------------\n\n");

        } catch (Exception ex) {
            LOGGER.error("Got JAXB Ecpetion in LogUtils while logging message",ex);
        }
    }
}

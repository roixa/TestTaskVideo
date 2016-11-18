package com.roix.testtaskvideo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by roix on 18.11.2016.
 */

@Root(name = "Domain")
public class Domain {
    @Attribute
    private String priority;
    @Attribute
    private String url;
}

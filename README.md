# ch.ethz.idsc.retina

<a href="https://travis-ci.org/idsc-frazzoli/retina"><img src="https://travis-ci.org/idsc-frazzoli/retina.svg?branch=master" alt="Build Status"></a>

Sensor interfaces and data processing in Java 8.

Version `0.0.1` 

The implementation includes

* obtaining data from wired sensor
* parsing of standard log files
* demo of data handling, for instance simple visualizations
* option to transmit and receive via the `lcm` protocol

The byte order of the binary data is `little endian` since the encoding is native on most architectures. 

# LIDAR

## HOKUYO URG-04LX-UG01

![urg04lx](https://user-images.githubusercontent.com/4012178/29029959-c052da4c-7b89-11e7-8b01-1b4efc3593c0.gif)

our code builds upon the
[urg_library-1.2.0](https://sourceforge.net/projects/urgnetwork/files/urg_library/)

## Velodyne HDL-32E

* 3D-point cloud visualization: see [video](https://www.youtube.com/watch?v=abOYEIdBgRs)

distance as 360[deg] panorama

![velodyne distances](https://user-images.githubusercontent.com/4012178/29020149-581e9236-7b61-11e7-81eb-0fc4577b687d.gif)

intensity as 360[deg] panorama

![intensity](https://user-images.githubusercontent.com/4012178/29026760-c29ebbce-7b7d-11e7-9854-9280594cb462.gif)

# DVS

## IniLabs DAVIS240C

.aedat files

* parsing and visualization
* conversion to text+png format
* loss-less compression of DVS events by the factor of 2
* compression of raw APS data by factor 8 (where the ADC values are reduced from 10 bit to 8 bit)

## streaming DAT files

![hdr](https://user-images.githubusercontent.com/4012178/27771907-a3bbcef4-5f58-11e7-8b0e-3dfb0cb0ecaf.gif)

## streaming DAVIS recordings

![shapes_6dof](https://user-images.githubusercontent.com/4012178/27771912-cb58ebb8-5f58-11e7-9566-79f3fbc5d9ba.gif)

## generating DVS from video sequence

![cat_final](https://user-images.githubusercontent.com/4012178/27771885-0eadb2aa-5f58-11e7-9f4d-78a57e610f56.gif)

## synthetic signal generation 

<table><tr>
<td>

![synth2](https://user-images.githubusercontent.com/4012178/27772611-32cc2e92-5f66-11e7-9d1f-ff15c42d54be.gif)

<td>

![synth1](https://user-images.githubusercontent.com/4012178/27772610-32af593e-5f66-11e7-8c29-64611f6ca3e6.gif)

</tr></table>

## Include in your project

Modify the `pom` file of your project to specify `repository` and `dependency` of the tensor library:

    <repositories>
      <repository>
        <id>retina-mvn-repo</id>
        <url>https://raw.github.com/idsc-frazzoli/retina/mvn-repo/</url>
        <snapshots>
          <enabled>true</enabled>
          <updatePolicy>always</updatePolicy>
        </snapshots>
      </repository>
    </repositories>
    
    <dependencies>
      <dependency>
        <groupId>ch.ethz.idsc</groupId>
        <artifactId>retina</artifactId>
        <version>0.0.1</version>
      </dependency>
    </dependencies>

## Dependencies

`retina` requires the libraries

* `ch.ethz.idsc.tensor`
* `ch.ethz.idsc.lcm-java`

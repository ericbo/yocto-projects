# Raspberry Pi
This build environment was created specifically for the origional Raspberry Pi Model B
(Broadcom BCM2835). The plan is to turn this template into one I can use for 
converting old household tech into IoT devices.

## Building

### Creating the build environment
First we need to setup out build environment. Lucky for us, the poky repo has a script
that will take care of this for us.
```bash
source poky/oe-init-build-env build
```

Assuming you don't exist your terminal, you should now be able to execute any bitbake
command. To build the image that will be use to flash the Raspberry Pi's SD card,
you can simply execute the following:
```bash
bitbake core-image-base
```

**Note:** The Image can be found in the following location 
`tmp/deploy/images/raspberrypi/rpi-basic-image-raspberrypi.wic`. This image can be use
directly with `dd` or the default `Disks` application packaged with Ubuntu 20.04.

## Hosting Your Own Package Repository
Yocto allows you to compile your recipies into a package format of your choice, we will
use deb for this example. This is great if you

### Generating your Package Files
After compiling you will need to generate package files so that the package manager
can properly crawl your website.

```bash
bitbake package-index
```

### Setting up a Webserver
For a quick and dirty approach, we will use pythons built in web server. To do this simply
run the following commands:

```bash
cd ./build/tmp/deploy/deb
python3 -m http.server
```

### Adding new Packages
In the event where you would like to add a new package, such as PHP, you can first check to see
if it's available in one of your loaded layers in `bblayers.conf` by running:

```bash
bitbake -s | grep php
```

In the event nothing is returned by this function, you will have to find a meta online that
contains the recipy for this package or creating your own recipy. Assuming the recipy is 
available, you can build and update your index with the following commands:

```bash
bitbake php
 bitbake package-index
```

### Adding your Repository to a Yocto Image
Having all your packages hosted on a web server is fun and all, but not very useful without
a way for your yocto image to communicate with it. To enable this, you will need to add
the following to your `build/local.conf` file.

```text
EXTRA_IMAGE_FEATURES += " package-management "

PACKAGE_FEED_URIS = "http://<hostname:port>"
PACKAGE_FEED_BASE_PATHS = "deb"
PACKAGE_FEED_ARCHS = "all arm1176jzfshf-vfp raspberrypi
```

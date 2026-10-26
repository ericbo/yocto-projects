SUMMARY = "Raspberry Pi remote backup station image"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

CORE_IMAGE_EXTRA_INSTALL += "\
    cups \
    hplip \
    python3 \
    python3-pip \
    python \
    ufw \
    git \
    usbutils \
    nano \
    curl \
    openssh-sftp-server \
    nginx \
    ipmitool \
"

# Secondary network interface configuration is managed by a separate
# systemd-networkd recipe: recipes-connectivity/secondary-net/secondary-net.bb

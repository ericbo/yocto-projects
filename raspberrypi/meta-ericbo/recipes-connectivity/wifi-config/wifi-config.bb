SUMMARY = "WiFi client configuration for wlan0 (wpa_supplicant + systemd-networkd)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Set these outside of version control, e.g. in build/conf/auto.conf:
#   WIFI_SSID = "MyNetwork"
#   WIFI_PSK = "MyPassphrase"
WIFI_SSID ??= ""
WIFI_PSK ??= ""

SRC_URI = "\
    file://wpa_supplicant-wlan0.conf \
    file://25-wlan0.network \
"

RDEPENDS_${PN} = "wpa-supplicant"

do_install() {
    if [ -z "${WIFI_SSID}" ] || [ -z "${WIFI_PSK}" ]; then
        bbfatal "WIFI_SSID and WIFI_PSK must be set (e.g. in build/conf/auto.conf)"
    fi

    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/
    sed -i -e 's|@WIFI_SSID@|${WIFI_SSID}|' -e 's|@WIFI_PSK@|${WIFI_PSK}|' \
        ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/25-wlan0.network ${D}${sysconfdir}/systemd/network/

    # Enable the wpa_supplicant@wlan0 template instance shipped by wpa-supplicant
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${systemd_system_unitdir}/wpa_supplicant@.service \
        ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
}

FILES_${PN} = "\
    ${sysconfdir}/wpa_supplicant \
    ${sysconfdir}/systemd/network \
    ${sysconfdir}/systemd/system/multi-user.target.wants \
"

<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Messages</h2>
                <xsl:for-each select="Messages/Message">
                    <div>
                        <h3>
                            <xsl:value-of select="SenderName"/>
                        </h3>
                        <p>
                            <xsl:value-of select="MessageContent"/>
                        </p>
                        <p>
                            <xsl:value-of select="Time"/>
                        </p>
                    </div>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
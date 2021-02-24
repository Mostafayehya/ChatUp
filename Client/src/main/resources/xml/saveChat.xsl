<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Students</h2>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>ID</th>
                        <th>Track</th>
                    </tr>
                    <xsl:for-each select="Students/Student">
                        <tr>
                            <td>
                                <xsl:value-of select="Name"/>
                            </td>
                            <td>
                                <xsl:value-of select="Id"/>
                            </td>
                            <td>
                                <xsl:value-of select="Track"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
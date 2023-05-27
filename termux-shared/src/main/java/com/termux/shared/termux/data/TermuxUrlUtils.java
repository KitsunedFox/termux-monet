package com.termux.shared.termux.data;

import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermuxUrlUtils {

    public static Pattern URL_MATCH_REGEX;

    public static Pattern getUrlMatchRegex() {
        if (URL_MATCH_REGEX != null)
            return URL_MATCH_REGEX;
        StringBuilder regex_sb = new StringBuilder();
        // Begin first matching group.
        regex_sb.append("(");
        // Begin scheme group.
        regex_sb.append("(?:");
        // The DAV proto.
        regex_sb.append("dav|");
        // The DICT proto.
        regex_sb.append("dict|");
        // The DNS proto.
        regex_sb.append("dns|");
        // File path.
        regex_sb.append("file|");
        // The Finger proto.
        regex_sb.append("finger|");
        // The FTP proto.
        regex_sb.append("ftp(?:s?)|");
        // The Git proto.
        regex_sb.append("git|");
        // The Gemini proto.
        regex_sb.append("gemini|");
        // The Gopher proto.
        regex_sb.append("gopher|");
        // The HTTP proto.
        regex_sb.append("http(?:s?)|");
        // The IMAP proto.
        regex_sb.append("imap(?:s?)|");
        // The IRC proto.
        regex_sb.append("irc(?:[6s]?)|");
        // The IPFS proto.
        regex_sb.append("ip[fn]s|");
        // The LDAP proto.
        regex_sb.append("ldap(?:s?)|");
        // The POP3 proto.
        regex_sb.append("pop3(?:s?)|");
        // The Redis proto.
        regex_sb.append("redis(?:s?)|");
        // The Rsync proto.
        regex_sb.append("rsync|");
        // The RTSP proto.
        regex_sb.append("rtsp(?:[su]?)|");
        // The SFTP proto.
        regex_sb.append("sftp|");
        // The SAMBA proto.
        regex_sb.append("smb(?:s?)|");
        // The SMTP proto.
        regex_sb.append("smtp(?:s?)|");
        // The Subversion proto.
        regex_sb.append("svn(?:(?:\\+ssh)?)|");
        // The TCP proto.
        regex_sb.append("tcp|");
        // The Telnet proto.
        regex_sb.append("telnet|");
        // The TFTP proto.
        regex_sb.append("tftp|");
        // The UDP proto.
        regex_sb.append("udp|");
        // The VNC proto.
        regex_sb.append("vnc|");
        // The Websocket proto.
        regex_sb.append("ws(?:s?)");
        // End scheme group.
        regex_sb.append(")://");
        // End first matching group.
        regex_sb.append(")");
        // Begin second matching group.
        regex_sb.append("(");
        // User name and/or password in format 'user:pass@'.
        regex_sb.append("(?:\\S+(?::\\S*)?@)?");
        // Begin host group.
        regex_sb.append("(?:");
        // IP address (from http://www.regular-expressions.info/examples.html).
        regex_sb.append("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)|");
        // Host name or domain.
        regex_sb.append("(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))?|");
        // Just path. Used in case of 'file://' scheme.
        regex_sb.append("/(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)");
        // End host group.
        regex_sb.append(")");
        // Port number.
        regex_sb.append("(?::\\d{1,5})?");
        // Resource path with optional query string.
        regex_sb.append("(?:/[a-zA-Z0-9:@%\\-._~!$&()*+,;=?/]*)?");
        // Fragment.
        regex_sb.append("(?:#[a-zA-Z0-9:@%\\-._~!$&()*+,;=?/]*)?");
        // End second matching group.
        regex_sb.append(")");
        URL_MATCH_REGEX = Pattern.compile(regex_sb.toString(), Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        return URL_MATCH_REGEX;
    }

    public static LinkedHashSet<CharSequence> extractUrls(String text) {
        LinkedHashSet<CharSequence> urlSet = new LinkedHashSet<>();
        Matcher matcher = getUrlMatchRegex().matcher(text);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            String url = text.substring(matchStart, matchEnd);
            urlSet.add(url);
        }
        return urlSet;
    }
}

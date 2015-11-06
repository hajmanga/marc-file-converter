UNIMARC is an international standard that specifies format structure for bibliographics records. In addition to UNIMARC, several national MARC standards exist. The textual formats aren't defined in standards; instead they enable easier re-processing of converted data.

The tool tries to be as standard compliant as possible, there is always room for interpretation (for example what fields to exclude, what values to fill in by default, etc), and specially the MARC21 to UNIMARC text conversion was made for data import from Library of Congress to yet-to-be-released small-scale bibliographic cataloguing system.

Nevertheless, the converter is extendable, which means that support for additional formats can be added with moderate effort by
  * describing field specific conversion action flows.
  * optionally defining validation action flow for the new output formats.
  * defining format specific writer.
The actions are implemented as simple Java classes (for example FieldIdChanger, or FieldIsUnique), and the flows are defined in Spring XML configuration files.

Please note that the tool is not currently being updated. If you wish to take ownership of it, just let me know.
# Introduction #

A brief description of marc-file-converter's use.

# Converting MARC records #

Compile the tool with

`mvn clean install`

or if there are failing tests with

` mvn clean install -Dmaven.test.skip`

Obtain source records for example from [Library of Congress](http://catalog.loc.gov) (search, and then save record). There are some examples in marc-file-converter's test/data directory.

Convert the record with

`java -jar target/marc-file-converter-0.9-jar-with-dependencies.jar -v -f MARC21 -t UNIMARC_TEXT -o result.txt example.marc21`

This will read MARC21 records from file example.marc21, write conversion results to result.txt in textual presentation of UNIMARC, and will also print out detected problems in the conversion.

# Extending the Converter #

The converter as it is now only can read and write MARC21 and Unimarc records. If you need to extend it to support additional MARC dialects, or enhance current conversion or validation, then following information could be useful:
  * format to format conversion is made of rules defined in Spring configutation files named Conversion-source-to-destination.xml. Each file defines a map which contains entries from field identifiers to actions. The actions are spring beans that do simple conversion actions in sequence. For example field 505 is filtered with table of contents splitter and then renamed to field 327.
  * validation works in similar manner, with files being named Validator-format.xml.
  * actual conversion and validation steps are implemented as Spring beans.

When adding a new conversion rule to existing conversion, modify suitable Converter-source-to-destination.xml, and if needed then add new Spring beans which implement the required logic.

When adding new validation rules, add required beans to Validation-format.xml.

When adding new support for new formats, add a new Conversion-source-to-destination.xml. This sounds simple, but is quite laborous task compared to enhancing existing conversions.
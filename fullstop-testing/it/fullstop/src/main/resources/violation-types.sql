INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('EC2_WITH_KEYPAIR', 'EC2 instance should not have a ssh key.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('WRONG_REGION', 'EC2 instance must not run in this region.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('MODIFIED_ROLE_OR_SERVICE', 'This roles/services cannot be changed.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('EC2_RUN_IN_PUBLIC_SUBNET', 'EC2 instances should not run in a public subnet, it is recommended to create an ELB instead.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('ACTIVE_KEY_TO_OLD', 'This active key is too old and should be refreshed.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('PASSWORD_USED', 'Password should not be used.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('WRONG_AMI', 'Wrong AMI used, select a Taupage one (the latest should be perfect)', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('MISSING_USER_DATA', 'User data not avaiable.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SECURITY_GROUPS_PORT_NOT_ALLOWED', 'Port not allowed in security groups.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('MISSING_SOURCE_IN_USER_DATA', 'Missing property "source" in user data.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('EC2_WITH_A_SNAPSHOT_IMAGE', 'EC2 should be deployed with an immutable tag.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SCM_URL_IS_MISSING_IN_KIO', 'Scm url is missing in KIO.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SCM_URL_IS_MISSING_IN_SCM_SOURCE_JSON', 'Scm url is missing in scm-source.json.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SCM_URL_NOT_MATCH_WITH_KIO', 'Scm url in scm-source.json and KIO are not matching.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('VERSION_APPROVAL_NOT_ENOUGH', 'Not enough version approvals in KIO.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('MISSING_VERSION_APPROVAL', 'Missing version approval.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('IMAGE_IN_PIERONE_NOT_FOUND', 'Image in pierone not found.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SCM_SOURCE_JSON_MISSING_FOR_IMAGE', 'scm-source.json is missing for this image.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('APPLICATION_VERSION_DOES_NOT_HAVE_A_VALID_ARTIFACT', 'Application version does not have a valid artifact.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('SOURCE_NOT_PRESENT_IN_PIERONE', 'Source not present in pierone.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('APPLICATION_NOT_PRESENT_IN_KIO', 'Application not present in KIO.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('APPLICATION_VERSION_NOT_PRESENT_IN_KIO', 'Application version not present in KIO.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('WRONG_USER_DATA', 'Wrong user data. See http://docs.stups.io/en/latest/components/taupage.html for details.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
INSERT INTO fullstop_data.violation_type(id, help_text, violation_severity, is_audit_relevant, created,created_by, version) VALUES ('WRONG_APPLICATION_MASTERDATA', 'Wrong application masterdata.', 'LOW_IMPACT',false, now(), 'mrandi', 0);
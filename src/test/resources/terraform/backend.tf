#
# terraform {
#   backend "remote" {
#     organization = "liquibase"
#     hostname     = "spacelift.io"
#     workspaces {
#       name = "liquibase-bigquery-testharness-tests"
#     }
#   }
# }
# data "terraform_remote_state" "liquibase-bigquery" {
#   backend = "remote"
#
#   config = {
#     hostname     = "spacelift.io"
#     organization = "liquibase"
#     workspaces = {
#       name = "liquibase-bigquery"
#     }
#   }
# }
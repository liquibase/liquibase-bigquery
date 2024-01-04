locals {
  deployer_email = "$var.deployer_sa@$project_id.iam.gserviceaccount.com"
}

module "bigquery" {
  source                     = "terraform-google-modules/bigquery/google"
  version                    = "~> 4.4"
  dataset_id                 = "harness_${var.env}_ds"
  dataset_name               = "harness_${var.env}_ds"
  description                = "DataSet for liquibase harness tests to be run" # updated the description accordingly
  project_id                 = var.project_id
  location                   = "US" # Update location if needed
  delete_contents_on_destroy = true
  dataset_labels             = {}
}

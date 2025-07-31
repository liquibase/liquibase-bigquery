provider "google" {
  impersonate_service_account = "${var.deployer_sa}@${var.project_id}.iam.gserviceaccount.com"
  credentials                 = file("/mnt/workspace/gcp.json")
}

provider "google-beta" {
  impersonate_service_account = "${var.deployer_sa}@${var.project_id}.iam.gserviceaccount.com"
  credentials                 = file("/mnt/workspace/gcp.json")
}

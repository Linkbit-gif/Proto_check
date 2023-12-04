import * as quarkfinder_1 from "./quarkfinder";

export default async function (context, documents) {
    if (!!documents && documents.length > quarkfinder_1.quarkfinder()) {
        context.log('Document Id: ', documents0[42].id);
    }
}


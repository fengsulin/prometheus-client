package com.lin.prometheus.prometheus.builder;

public enum QueryBuilderType {
    RangeQuery{
        @SuppressWarnings("unchecked")
        @Override
        public RangeQueryBuilder newInstance(String prometheusUri){
            return new RangeQueryBuilder(prometheusUri);
        }
    },
    InstantQuery{
        @SuppressWarnings("unchecked")
        @Override
        public InstantQueryBuilder newInstance(String prometheusUri){
            return new InstantQueryBuilder(prometheusUri);
        }
    },	SeriesMetaQuery{

        @SuppressWarnings("unchecked")
        @Override
        public QueryBuilder newInstance(String prometheusUrl) {
            return new SeriesMetaQueryBuilder(prometheusUrl);
        }

    },
    LabelMetaQuery{

        @SuppressWarnings("unchecked")
        @Override
        public QueryBuilder newInstance(String prometheusUrl) {
            return new LabelMetaQueryBuilder(prometheusUrl);
        }

    },

    TargetMetaQuery{

        @SuppressWarnings("unchecked")
        @Override
        public QueryBuilder newInstance(String prometheusUrl) {
            return new TargetMetaQueryBuilder(prometheusUrl);
        }

    },

    AlertManagerMetaQuery{

        @SuppressWarnings("unchecked")
        @Override
        public QueryBuilder newInstance(String prometheusUrl) {
            return new AlertManagerMetaQueryBuilder(prometheusUrl);
        }

    },

    StatusMetaQuery{

        @SuppressWarnings("unchecked")
        @Override
        public QueryBuilder newInstance(String prometheusUrl) {
            return new StatusMetaQueryBuilder(prometheusUrl);
        }

    };

    @SuppressWarnings("unchecked")
    public abstract <T extends QueryBuilder> T newInstance(String prometheusUri);
}
